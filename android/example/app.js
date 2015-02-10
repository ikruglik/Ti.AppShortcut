var win = Ti.UI.createWindow({
    backgroundColor:'white'
});
win.open();

var appshortcut = require('ti.ikruglik.appshortcut');

var result;

var buttonCreate = Ti.UI.createButton({
    top: 50,
    title: 'Create shortcut' 
});

buttonCreate.addEventListener('click', function ()
{
    result = appshortcut.createShortcut();
    alert('Shortcut created: '+result);    
});
win.add(buttonCreate);

var buttonDelete = Ti.UI.createButton({
    top: 100,
    title: 'Delete shortcut' 
});
win.add(buttonDelete);

buttonDelete.addEventListener('click', function ()
{
    result = appshortcut.deleteShortcut();
    alert('Shortcut deleted: '+result);    
});
