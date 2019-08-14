module.exports = function (config) {
  config.set({
    mutator: "typescript",
    packageManager: "npm",
    reporters: ["html", "clear-text", "progress"],
    testRunner: "jest",
    transpilers: ["typescript"],
    coverageAnalysis: "off",
    tsconfigFile: "tsconfig.json",
    mutate: ["src/**/*.ts", "!src/**/*.test.ts"],
    jest: {
      projectType: 'custom',
      config: require('./jest.config.js'),
      enableFindRelatedTests: true,
    }
  });
};
