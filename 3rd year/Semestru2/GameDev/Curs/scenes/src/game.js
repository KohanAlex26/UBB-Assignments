class Scene1 extends Phaser.Scene{
    constructor() {
        super("bootGame");
    }
    create(){
        console.log('s1');
        this.add.text(20,20,"Loading game...");
        setTimeout(()=> {
            this.scene.start("playGame");
            },3000);
    }
}

class Scene2 extends Phaser.Scene{
    constructor() {
        super("playGame");
    }
    create(){
        console.log('s2');
        this.add.text(20,20,"Playing game",{font:"25px Arial",fill:"yellow"});
    }
}



var config={
    width:800,
    height:600,
    backgroundColor:0x000000,
    scene:[Scene1,Scene2]
}

var game=new Phaser.Game(config);