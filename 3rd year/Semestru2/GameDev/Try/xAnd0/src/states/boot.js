class Boot extends Phaser.Stage {
  preload() {
    this.load.image("preloader", "assets/preloader.gif");
  }

  create() {
    this.game.stage.backgroundColor = "#FFFFFF";
    this.game.state.start("preload");
  }
}

export default Boot;
