module.exports = {
  plugins: [
    require('postcss-preset-env')({}),
    require('cssnano')({
      preset: 'default',
    }),
    require('@fullhuman/postcss-purgecss')({
      content: ['src/**/*.ts', 'src/**/*.css', '!src/**/*.test.ts']
    })
  ]
}
