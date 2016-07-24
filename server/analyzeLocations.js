const geolib = require('geolib');

const stageCoordinates = require ('./data/stageCoordinates.js')

const countPerStage = allLocations => {
  const stageCounts = {};
  Object.keys(stageCoordinates).forEach(stage => stageCounts[stage] = 0);
  allLocations.forEach(location => {
    const locationLatLong = {latitude: location.lat, longitude: location.long};
    const nearestStage = geolib.findNearest(locationLatLong, stageCoordinates);
    stageCounts[nearestStage.key]++;
  })
  return stageCounts;
}

module.exports = allLocations => {
  const stageCounts = countPerStage(allLocations);
  const maxCount = Object.keys(stageCounts).reduce((highestSoFar, stage) => {
    if (stageCounts[stage] > highestSoFar) {
      return stageCounts[stage];
    }
    return highestSoFar;
  }, 0)
  return normalizedCounts = Object.keys(stageCounts).reduce((result, stage) => {
    result[stage] = stageCounts[stage] / maxCount;
    return result;
  }, {})
}