// const aStar = require('a-star');
const bestPath = require('./bestPath');

const { req, inputs } = require('./inputs');

const mapGraph = (inputs, numPersons, numHandBaggage, numExtraBaggage) => {
  const graph = {},
    pathIds = {};
  for (let i = 0; i < inputs.length; i++) {
    const flight = inputs[i];
    if (!graph[flight.departureAirport]) {
      graph[flight.departureAirport] = {};
    }
    const distance =
      numPersons * flight.price +
      numHandBaggage * flight.handBaggagePrice +
      numExtraBaggage * flight.extraBaggagePrice;
    if (
      !graph[flight.departureAirport][flight.arrivalAirport] ||
      graph[flight.departureAirport][flight.arrivalAirport] > distance
    ) {
      graph[flight.departureAirport][flight.arrivalAirport] = distance;
      pathIds[flight.departureAirport + flight.arrivalAirport] = flight.id;
    }
  }

  return { graph, pathIds };
};

const mapPath = (path, pathIds) => {
  const ret = [];
  for (let i = 1; i < path.length; i++) {
    const id = pathIds[path[i - 1] + path[i]];
    ret.push(id);
  }
  return ret;
};

// MAIN

for (let i = 0; i < req.length; i++) {
  const {
    numPersons,
    numHandBaggage,
    numExtraBaggage,
    departureAirport,
    arrivalAirport,
  } = req[i];

  const { graph, pathIds } = mapGraph(
    inputs[i],
    numPersons,
    numHandBaggage,
    numExtraBaggage
  );

  //   const {path, cost} = aStar({
  //     start: departureAirport,
  //     isEnd: (n) => n === arrivalAirport,
  //     neighbor: (n) => Object.keys(graph[n] || []),
  //     distance: (a, b) => graph[a][b] || 'Infinity',
  //     heuristic: () => 0,
  //   });

  const { path, distance } = bestPath(graph, departureAirport, arrivalAirport);
  const cost = distance;

  console.log(`PROBLEM ${i + 1} cost ${cost}`, mapPath(path, pathIds));
}
