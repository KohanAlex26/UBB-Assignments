
    const start = () => nw.Window.open('index.html', {
      position: 'center',
      new_instance: true
    });
    nw.App.on('open', start);
    start();