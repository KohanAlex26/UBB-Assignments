class GameTitle {
  constructor() {
    console.log('GameTitle');
  }

  create() {
    console.log('GameTitle - create');
    var gameTitle = this.game.add.sprite(160, 160, "gametitle");
    gameTitle.anchor.setTo(0.5, 0.5);
    var playButton = this.game.add.button(160, 320, "play", this.playTheGame, this);
    playButton.anchor.setTo(0.5, 0.5);
  }

  playTheGame() {
    this.game.state.start("TheGame");
  }
}