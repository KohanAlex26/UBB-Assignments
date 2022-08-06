import { io } from "socket.io-client";
import Player from "../entity/player";

class Game extends Phaser.Stage {
  create() {
    this.socket = io.connect(window.location.host);
    this.players = [];
    this.nr=1;

    //system stuff
    this.game.physics.startSystem(Phaser.Physics.P2JS);
    this.game.physics.p2.setImpactEvents(true);
    this.game.physics.p2.restitution = 0.8;
  
    //background color
    this.game.stage.backgroundColor = "#fff";

    //boxes
    const tmpPreload = this.game.cache.getImage("grid");
    
    this.sprite1 = this.add.sprite(
      (this.game.width - tmpPreload.width) / 2,
      (this.game.height - tmpPreload.height) / 2,
      "grid"
    );
    
    // this.sprite1.input.onDown.add(function(){console.log("ASDASD")}, this.sprite1);


    this.sprite2 = this.add.sprite(
      (this.game.width - tmpPreload.width) / 2+40,
      (this.game.height - tmpPreload.height) / 2,
      "grid"
    );
    this.sprite3 = this.add.sprite(
      (this.game.width - tmpPreload.width) / 2+80,
      (this.game.height - tmpPreload.height) / 2,
      "grid"
    );
    this.sprite4 = this.add.sprite(
      (this.game.width - tmpPreload.width) / 2,
      (this.game.height - tmpPreload.height) / 2+40,
      "grid"
    );
    this.sprite5 = this.add.sprite(
      (this.game.width - tmpPreload.width) / 2+40,
      (this.game.height - tmpPreload.height) / 2+40,
      "grid"
    );
    this.sprite6 = this.add.sprite(
      (this.game.width - tmpPreload.width) / 2+80,
      (this.game.height - tmpPreload.height) / 2+40,
      "grid"
    );
    this.sprite7 = this.add.sprite(
      (this.game.width - tmpPreload.width) / 2,
      (this.game.height - tmpPreload.height) / 2+80,
      "grid"
    );
    this.sprite8 = this.add.sprite(
      (this.game.width - tmpPreload.width) / 2+40,
      (this.game.height - tmpPreload.height) / 2+80,
      "grid"
    );
    this.sprite9 = this.add.sprite(
      (this.game.width - tmpPreload.width) / 2+80,
      (this.game.height - tmpPreload.height) / 2+80,
      "grid"
    );
    
    this.setEventHandlers(this.game);
  }

  setEventHandlers() {
   
    this.socket.on("connect", () => {
      this.player = new Player(this.game, this.socket);
      this.socket.emit("new_player", this.player.toJson());
      


      // new player
      this.socket.on("new_player", () => {
        // console.log("A");
      });

      this.socket.on("logout", (id) => {
        delete this.players[id];
      });

      
    });
  }

  update() {
    // if (this.player) {
    //   this.player.update(this.game);
    // }

    // this.game.debug.cameraInfo(this.game.camera, 32, 32);
    // this.game.debug.text("fps: " + this.game.time.fps || "--", 32, 140);
  }

}

export default Game;
