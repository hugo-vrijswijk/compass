/* eslint-disable @typescript-eslint/no-var-requires */
/* eslint-disable no-undef */
const merge = require('webpack-merge');
const common = require('./webpack.config.js');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const OptimizeCssAssetsPlugin = require('optimize-css-assets-webpack-plugin');
const WorkboxPlugin = require('workbox-webpack-plugin');

module.exports = merge(common, {
  mode: 'production',
  plugins: [
    new MiniCssExtractPlugin({
      filename: '[name].[hash].css',
      chunkFilename: '[name].[hash].css'
    }),
    new OptimizeCssAssetsPlugin(),
    new WorkboxPlugin.GenerateSW({
      importWorkboxFrom: 'local',
      cleanupOutdatedCaches: true,
      exclude: ['index.html', /\.map$/, /^manifest.*\.js$/, /^api\//]
    })
  ],
  module: {
    rules: [
      {
        test: /\.css$/,
        use: [MiniCssExtractPlugin.loader, 'css-loader', 'postcss-loader']
      }
    ]
  },
  optimization: {
    splitChunks: {
      chunks: 'all'
    }
  }
});
