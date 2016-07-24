const queries = require('./queries.js');
const analyzeLocations = require('./analyzeLocations.js');

exports.postLocation = (req, res) => {
  console.log(req.body);
  console.log(req.body.location);
  res.status(204).send();
  const time = new Date().getTime()
  const {lat, long} = req.body.coordinates;
  const params = {lat, long, time};
  queries.writeLocation(params);
}

exports.getAllData = (req, res, next) => {
  // const currentTime = new Date().getTime(); //TODO: switch back to actually generating the time for non-demo
  const currentTime = 1469330301930; 
  const cutoffTime = currentTime - 30000;
  params = { cutoffTime };
  queries.readAllData(params, (err, response) => {
    if (err) {
      console.error(err);
      next();
    } else {
      const allLocations = analyzeLocations(response);
      res.status(200).json({accesibility: allLocations});
    }
  })
}