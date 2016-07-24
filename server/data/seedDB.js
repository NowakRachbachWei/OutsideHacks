//TODO: rand the distance from the stage between 0 and .001, on both axes

const stageCoordinates = require('./stageCoordinates.js');
const queries = require('../queries.js');

Object.keys(stageCoordinates).forEach(stage => {
  const lat = stageCoordinates[stage].latitude;
  const long = stageCoordinates[stage].longitude;
  const time = 1469330301930;
  const fans = Math.floor(Math.random() * 1000);
  for (let i = 0; i < fans; i++) {
    queries.writeLocation({lat, long, time});
  }
})