const fs = require("fs");
const path = require("path");
const express = require("express");
const app = express();
const server = require("http").Server(app);
const io = require("socket.io")(server);
const { createServer: createViteServer } = require("vite");

function randomIntInc(low, high) {
  return Math.floor(Math.random() * (high - low + 1) + low);
}

(async () => {
  // http
  const vite = await createViteServer({
    server: { middlewareMode: true },
  });
  app.use(vite.middlewares);
  app.use("*", async (req, res) => {
    let template = fs.readFileSync(
      path.resolve(__dirname, "index.html"),
      "utf-8"
    );
    res.status(200).set({ "Content-Type": "text/html" }).end(template);
  });

  // init
  const users = [];

  // socket
  io.on("connection", function (socket) {


    let me = false;
    socket.on("new_player", function (user) {
      me = user;

      for (let k in users) {
        socket.emit("new_player", users[k]);
      }

      users[me.id] = me;
      socket.broadcast.emit("new_player", user);
    });


    
    socket.on("disconnect", function () {
      if (!me) {
        return false;
      }
      delete users[me.id];
      socket.broadcast.emit("logout", me.id);
    });


  });

  server.listen(3000, function () {
    console.log("listening on *:3000");
  });
})();
