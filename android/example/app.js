// open a single window
var win = Ti.UI.createWindow({
    backgroundColor:'white'
});
win.open();

var appshortcut = require('ti.ikruglik.appshortcut');

alert('Shortcut created: '+appshortcut.createShortcut('My Application Shortcut'));
