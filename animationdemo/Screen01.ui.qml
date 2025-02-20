

/*
This is a UI file (.ui.qml) that is intended to be edited in Qt Design Studio only.
It is supposed to be strictly declarative and only uses a subset of QML. If you edit
this file manually, you might introduce QML code that is not supported by Qt Design Studio.
Check out https://doc.qt.io/qtcreator/creator-quick-ui-forms.html for details on .ui.qml files.
*/
import QtQuick
import QtQuick.Controls
import Animationdemo

Rectangle {
    id: rectangle
    width: Constants.width
    height: Constants.height

    color: Constants.backgroundColor

    MyCanvas {
        id: myCanvas
        x: 180
        y: 147

        Rectangle {
            id: rectangle1
            color: "#ffffff"
            border.width: 2
            anchors.fill: parent
            z: -1
        }
    }
    states: [
        State {
            name: "clicked"
        }
    ]
}
