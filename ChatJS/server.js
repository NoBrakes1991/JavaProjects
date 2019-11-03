const app = require('express')();
const http = require('http').createServer(app);
const session = require('express-session');
const io = require('socket.io')(http);
const MemoryStore = require('memorystore')(session);
const express = require('express')
app.use('/static', express.static('static'));

var passport = require('passport'),
    FacebookStrategy = require('passport-facebook').Strategy,
    TwitterStrategy = require('passport-twitter').Strategy,
    VKontakteStrategy = require('passport-vk-strategy').Strategy,
    YandexStrategy = require('passport-yandex').Strategy;


const store = new MemoryStore({
    checkPeriod: 86400000 
});

app.use(session({
    secret: '326159487326159487',
    resave: false,
    saveUninitialized: true,
    store: store,
}));
app.use(passport.initialize());
app.use(passport.session());

passport.serializeUser(function (user, done) {
    done(null, user);
});

passport.deserializeUser(function (user, done) {
    done(null, user);
});


passport.use(new FacebookStrategy({
        clientID: '1734374453359296',
        clientSecret: '3d9e9a12640e2645d30ef03adc18f802',
        callbackURL: "http://localhost:3000/auth/facebook/callback"
    },
    function (accessToken, refreshToken, profile, done) {
        done(null, {id: profile.id, name: profile.displayName});
    }
));

passport.use(new VKontakteStrategy(
    {
        clientID: '7041549', // VK.com docs call it 'API ID', 'app_id', 'api_id', 'client_id' or 'apiId'
        clientSecret: 'Sc58Jh6PQhHotwnmLXU2',
        callbackURL: "http://192.168.0.100:3000/auth/vkontakte/callback"
    },
    function myVerifyCallbackFn(accessToken, refreshToken, params, profile, done) {
        done(null, {id: profile.id, name: profile.displayName});
    }
));

passport.use(new TwitterStrategy({
        consumerKey: '',
        consumerSecret: '',
        callbackURL: "http://192.168.0.100:3000/auth/twitter/callback"
    },
    function(token, tokenSecret, profile, cb) {
        User.findOrCreate({ twitterId: profile.id }, function (err, user) {
            return cb(err, user);
        });
    }
));

passport.use(new YandexStrategy({
        clientID: '',
        clientSecret: '',
        callbackURL: "http://192.168.0.100:3000/auth/yandex/callback"
    },
    function(accessToken, refreshToken, profile, done) {
        done(null, {id: profile.id, name: profile.displayName});
    }
));

function authenticationMiddleware() {
    return function (req, res, next) {
        if (req.isAuthenticated()) {
            return next()
        }
        res.redirect('/login')
    }
}

app.get('/auth/facebook', passport.authenticate('facebook'));
app.get('/auth/facebook/callback', passport.authenticate('facebook', {
    successRedirect: '/',
    failureRedirect: '/login'
}));

app.get('/auth/vkontakte', passport.authenticate('vkontakte'));
app.get('/auth/vkontakte/callback', passport.authenticate('vkontakte', {
    successRedirect: '/',
    failureRedirect: '/login'
}));

app.get('/auth/twitter', passport.authenticate('twitter'));
app.get('/auth/twitter/callback', passport.authenticate('twitter', {
    successRedirect: '/',
    failureRedirect: '/login'
}));

app.get('/auth/yandex', passport.authenticate('yandex'));
app.get('/auth/yandex/callback', passport.authenticate('yandex',  {
    successRedirect: '/',
    failureRedirect: '/login'
}));

app.get('/', authenticationMiddleware(), function (req, res) {
    res.sendFile(__dirname + '/index.html');
});

app.get('/login', function (req, res) {
    res.sendFile(__dirname + '/auth.html');
});


var log = [];
const passportSocketIo = require("passport.socketio");
io.set('authorization', passportSocketIo.authorize({
    cookieParser: require('cookie-parser'),
    key: 'connect.sid',
    secret: '326159487326159487',
    success: onAuthorizeSuccess,
    fail: onAuthorizeFail,
    store: store
}));

function onAuthorizeSuccess(data, accept) {
    console.log('successful connection to socket.io');
    accept(null, true);
    accept();
}

function onAuthorizeFail(data, message, error, accept) {
    if (error)
        throw new Error(message);
    console.log('failed connection to socket.io:', message);
    accept(null, false);
    if (error)
        accept(new Error(message));
}


io.on('connection', function (socket) {
    console.log('a user connected');

    for (var i = log.length - 1; i >= 0; i--) {
        socket.emit('message', log[i]);
    }

    socket.on('disconnect', function () {
        console.log('user disconnected');
    });

    socket.on('message', function (msg) {
        msg.user = socket.request.user;
        log.unshift(msg);
        log.length = 5;
        io.emit('message', msg);
    });
});


http.listen(3000, function () {
    console.log('listening on *:3000');
});

