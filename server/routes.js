const controller = require('./controller.js')

module.exports = app => {
  app.post('/api/location', controller.postLocation);
  app.get('/api', controller.getAllData);
}