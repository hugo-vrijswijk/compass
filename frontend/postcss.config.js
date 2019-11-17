module.exports = {
  plugins: [
    require('postcss-preset-env')({}),
    require('postcss-custom-properties')({}),
    require('postcss-nesting')({}),
    require('cssnano')({
      preset: 'default',
    }),
    require('@fullhuman/postcss-purgecss')({
      content: ['src/**/*.ts', '!src/**/*.test.ts'],
    }),
  ],
};
