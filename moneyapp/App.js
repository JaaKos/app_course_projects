import { Text, SafeAreaView, StyleSheet, TextInput, View, Button } from 'react-native';

export default function App() {
  return (
    <SafeAreaView style={styles.container}>
      <Text style={{ textAlign: 'center', fontSize: 32, padding: 60 }}>Valuuttamuunnin</Text>
      <View style={styles.row}>
        <TextInput style={styles.inputBox} keyboardType="numeric" />
        <Text style={styles.h2}>€ = $</Text>
        <TextInput style={styles.inputBox} keyboardType="numeric" editable="false" />
      </View>
      <View style={styles.buttonView}>
        <Button style={styles.button} title="Laske"/>
      </View>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    backgroundColor: '#ecf0f1',
    padding: 8,
  },
  row: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
  },
  inputBox: {
    padding: 10,
    margin: 30,
    width: 60,
    borderColor: 'gray',
    borderWidth: 1,
    textAlign: 'center',
  },
  h2: {
    fontSize: 24,
    marginHorizontal: 20,
  },
  button: {
    backgroundColor: 'blue',
    padding: 10,
    borderRadius: 5,
    marginTop: 20,
    alignSelf: 'center',
  },
  buttonView: {
    width: 150,
    alignSelf:'center'
  }
});