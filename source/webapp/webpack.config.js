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
    // resolve TypeScript file
    resolve: {
        // Add `.ts` and 'js' as a resolvable extension.
        extensions: ['.ts', '.js'],
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
            }
        ]
    }
};