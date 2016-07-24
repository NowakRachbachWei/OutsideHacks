const pgp = require('pg-promise')();

if (process.env.SERVER === 'deployed') {
  const env = require('../.env');
  var clientLink = {
    user: 'outsidehacks',
    database: 'outsidehacks',
    password: env.password,
    host: env.host,
    port: 5432,
  };
} else {
  var clientLink = 'postgres://localhost:5432/outsidehacks';
}

module.exports = pgp(clientLink);