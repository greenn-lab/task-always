module.exports = {
  'login test': function (browser) {
    browser
      .url(process.env.VUE_DEV_SERVER_URL)
      .waitForElementVisible('#app', 5000)
      .assert.containsText('h1', 'Task Always')
      .end()
  }
}
