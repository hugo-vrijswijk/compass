module.exports = {
  title: 'Compass',
  tagline: 'Manage all your media downloads in one',
  url: 'https://hugo-vrijswijk.github.io',
  baseUrl: '/compass/',
  favicon: 'img/favicon.ico',
  organizationName: 'hugo-vrijswijk',
  projectName: 'compass',
  themeConfig: {
    navbar: {
      title: 'Compass',
      logo: {
        alt: 'Compass Logo',
        src: 'img/logo.svg',
      },
      links: [
        { to: 'docs/introduction', label: 'Docs', position: 'left' },
        { to: 'blog', label: 'Blog', position: 'left' },
        {
          href: 'https://github.com/hugo-vrijswijk/compass',
          label: 'GitHub',
          position: 'right',
        },
      ],
      algolia: {
        apiKey: 'abee8c9b5a5ad09c8a05148f7566ed0e',
        indexName: 'compass-docs',
        algoliaOptions: {},
      },
    },
    footer: {
      style: 'dark',
      links: [
        {
          title: 'Docs',
          items: [
            {
              label: 'Style Guide',
              to: 'docs/doc1',
            },
            {
              label: 'Second Doc',
              to: 'docs/doc2',
            },
          ],
        },
        {
          title: 'Community',
          items: [
            {
              label: 'Stack Overflow',
              href: 'https://stackoverflow.com/questions/tagged/docusaurus',
            },
            {
              label: 'Discord',
              href: 'https://discordapp.com/invite/docusaurus',
            },
          ],
        },
        {
          title: 'Social',
          items: [
            {
              label: 'Blog',
              to: 'blog',
            },
            {
              label: 'GitHub',
              href: 'https://github.com/facebook/docusaurus',
            },
            {
              label: 'Twitter',
              href: 'https://twitter.com/docusaurus',
            },
          ],
        },
      ],
    },
    googleAnalytics: {
      trackingID: 'UA-155360939-1',
    },
  },
  presets: [
    [
      '@docusaurus/preset-classic',
      {
        docs: {
          sidebarPath: require.resolve('./sidebars.js'),
          editUrl:
            'https://github.com/hugo-vrijswijk/compass/tree/master/website/',
          showLastUpdateTime: true,
        },
        theme: {
          customCss: require.resolve('./src/css/custom.css'),
        },
      },
    ],
  ],
};
