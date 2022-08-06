class GameOver {
  constructor() {
    console.log('GameOver');
  }

  init(score) {
    console.log('GameOver - init');
    alert("You scored: " + score)
  }

  create() {
    console.log('GameOver - create');
    var gameOverTitle = this.game.add.sprite(160, 160, "gameover");
    gameOverTitle.anchor.setTo(0.5, 0.5);
    var playButton = this.game.add.button(160, 320, "play", this.playTheGame, this);
    playButton.anchor.setTo(0.5, 0.5);
  }

  playTheGame() {
    this.game.state.start("TheGame");
  }

}