class Player {
  constructor(game, socket) {
    this.game = game;
    this.socket = socket;
    this.id = socket.io.engine.id;

  }


  toJson() {
    return {
      id: this.id,
    };
  }

  update(game) {
    // game.physics.arcade.moveToPointer(this.sprite, this.speed);

    // game.debug.text("speed: " + this.sprite.speed, 32, 120);
    // game.debug.text(
    //   this.sprite.mass,
    //   this.sprite.x - game.camera.x - 10,
    //   this.sprite.y - game.camera.y + 5
    // );

    // this.socket.emit("move_player", this.toJson());
  }
}

export default Player;
