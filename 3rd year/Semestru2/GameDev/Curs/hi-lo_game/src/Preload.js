class Preload {
  constructor() {
    console.log('Preload');
  }

  preload() {
    console.log('Preload - preload', this);
    var loadingBar = this.add.sprite(160, 240, 'loading');
    loadingBar.anchor.setTo(0.5, 0.5);
    this.load.setPreloadSprite(loadingBar);
    this.game.load.spritesheet('numbers', 'assets/numbers.png', 100, 100);
    this.game.load.image('gametitle', 'assets/gametitle.png');
    this.game.load.image('play', 'assets/play.png');
    this.game.load.image('higher', 'assets/higher.png');
    this.game.load.image('lower', 'assets/lower.png');
    this.game.load.image('gameover', 'assets/gameover.png');
  }

  create() {
    console.log('Preload - create');
    this.game.state.start('GameTitle');
  }
}