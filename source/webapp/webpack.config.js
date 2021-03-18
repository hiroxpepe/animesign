const path = require('path');

module.exports = {
    entry: [
        './src/main/resources/assets/index.ts'
    ],
    output: {
        path: path.join(__dirname, 'src/main/webapp/docroot/scripts'),
        filename: 'animesign.core.js'
    },
    devtool: 'source-map',
    // resolve TypeScript and Vue file
    resolve: {
        // Add `.ts` and `.vue` as a resolvable extension.
        extensions: ['.ts', '.vue', '.js', '.html'],
        alias: {
            vue: 'vue/dist/vue.js'
        }
    },
    module: {
        rules: [
            {
                test: /\.ts$/,
                exclude: /node_modules|vue\/src/,
                use: [
                    {
                        loader: 'ts-loader',
                        options: {
                            appendTsSuffixTo: [/\.vue$/]
                        }
                    }
                ]
            },
            {
                test: /\.vue$/,
                loader: 'vue-loader',
                options: {
                    esModule: true
                }
            },
            {
                test: /\.html$/,
                loader: 'vue-html-loader',
                options: {
                    hmr: false // disables Hot Modules Replacement
                }
            }
        ]
    }
};