const nextNode = (distances, visited) => {
  let next = null;
  for (let d in distances) {
    if (
      !visited.includes(d) &&
      (next === null || distances[d] < distances[next])
    ) {
      next = d;
    }
  }
  return next;
};

const bestPath = (graph, from, to) => {
  const parents = {
    [to]: null,
  };
  for (let d in graph[from]) {
    parents[d] = from;
  }

  let distances = {
    [to]: 'Infinity',
    ...graph[from],
  };

  const visited = [];

  let next = nextNode(distances, visited);

  while (next) {
    const dist = distances[next];
    const destinations = graph[next];
    // console.log(next, visited, parents, distances);

    for (let d in destinations) {
      if (d === from) {
        continue;
      }
      const newDist = dist + destinations[d];
      if (
        distances[d] === null ||
        distances[d] === undefined ||
        distances[d] > newDist
      ) {
        distances[d] = newDist;
        parents[d] = next;
        // console.log(distances, parents);
      }
    }

    visited.push(next);
    next = nextNode(distances, visited);
  }
  //   console.log(next, visited, parents, distances);

  const path = [to];
  let p = parents[to];
  while (p) {
    path.push(p);
    p = parents[p];
  }

  path.reverse();

  return {
    path,
    distance: distances[to],
  };
};

module.exports = bestPath;

//   console.log(bestPath(graph, "F", "E"));
