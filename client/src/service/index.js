function request(value, url, type) {
  return new Promise((resolver, reject) => {
    fetch(`${url}`, {
      method: type,
      body: value && JSON.stringify(value),
      headers: {
        "Content-type": "application/json",
      },
    })
      .then(async (response) => {
        resolver(response);
        return;
      })
      .catch((err) => {
        reject(err);
      });
  });
}
export default { request };
