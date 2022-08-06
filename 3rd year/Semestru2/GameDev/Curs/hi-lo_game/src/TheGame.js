class TheGame {
  constructor() {
    console.log('TheGame');
    this.spriteNumber = null;
    this.number = 0;
    this.workingButtons = true;
    this.higher = true;
    this.score = 0;
  }

  create() {
    console.log('TheGame - create');
    this.number = Math.floor(Math.random() * 10);
    this.spriteNumber = this.game.add.sprite(160, 240, "numbers");
    this.spriteNumber.anchor.setTo(0.5, 0.5);
    this.spriteNumber.frame = this.number;
    var higherButton = this.game.add.button(160, 100, "higher", this.clickedHigher, this);
    higherButton.anchor.setTo(0.5, 0.5);
    var lowerButton = this.game.add.button(160, 380, "lower", this.clickedLower, this);
    lowerButton.anchor.setTo(0.5, 0.5);
  }

  paused() {
    console.log('TheGame - paused');
  }

  resumed() {
    console.log('TheGame - resumed');
  }

  clickedHigher() {
    this.higher = true;
    this.tweenNumber(true);
  }

  clickedLower() {
    this.higher = false;
    this.tweenNumber(false);
  }

  tweenNumber(higher) {
    if (this.workingButtons) {
      this.workingButtons = false;
      var exitTween = this.game.add.tween(this.spriteNumber);
      exitTween.to({x: 420}, 500);
      exitTween.onComplete.add(this.exitNumber, this);
      exitTween.start();
    }
  }

  exitNumber() {
    this.spriteNumber.x = -180;
    this.spriteNumber.frame = Math.floor(Math.random() * 10);
    var enterTween = this.game.add.tween(this.spriteNumber);
    enterTween.to({x: 160}, 500);
    enterTween.onComplete.add(this.enterNumber, this);
    enterTween.start();
  }

  enterNumber() {
    this.workingButtons = true;
    if ((this.higher && this.spriteNumber.frame < this.number) || (!this.higher && this.spriteNumber.frame > this.number)) {
      this.game.state.start("GameOver", true, false, this.score);
    }
    else {
      this.score++;
      this.number = this.spriteNumber.frame;
    }
  }

}
