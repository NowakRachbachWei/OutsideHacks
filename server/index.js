const express = require('express');
const routes = require('./routes.js');

const bodyParser = require('body-parser'); 

const app = express();

const host = process.env.HOST || 'localhost';
const port = process.env.PORT || process.env.PORT_API || 8080;

app.use(bodyParser.json());
routes(app);

app.listen(port, (err) => {
  if (err) {
    return process.stdout
    .write(`Server error: ${err}\n`);
  }
  return process.stdout
  .write(`Server online at http://${host}:${port}. Use <ctrl-c> to stop server.\n`);
});

module.exports = app;