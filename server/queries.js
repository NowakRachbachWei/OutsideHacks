const db = require('./dbConnection.js');

exports.writeLocation = params => {
  db.none("INSERT INTO location (lat, long, ip, time) VALUES(${lat}, ${long}, 127001, ${time})", params)
    .then(response => console.log(response))
    .catch(err => console.error(err));
}

exports.readAllData = (params, cb) => {
  db.any("SELECT lat, long, time FROM location WHERE time > ${cutoffTime}", params)
    .then(response => cb(null, response))
    .catch(err => cb(err, null));
}