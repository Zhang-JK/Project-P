import {createProxyMiddleware} from 'http-proxy-middleware';

module.exports = function setupProxy(app: any) {
    app.use('/api', createProxyMiddleware({
        target: 'http://localhost:8080',
        changeOrigin: true,
        secure: false,
        // cookiePathRewrite: {'/'},
    }));
    app.use('/api/login', createProxyMiddleware({
        target: 'http://localhost:8080/api/login',
        changeOrigin: true,
        // pathRewrite: {
        //     '^/api': ''
        // },
        // cookiePathRewrite: {'/'},
    }));
}
// const app = express();
//
//
