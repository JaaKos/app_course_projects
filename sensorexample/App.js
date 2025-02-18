import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';
import { Accelerometer, Gyroscope } from 'expo-sensors';
import { useState, useEffect } from 'react';

export default function App() {
  const [acceleration, setAcceleration] = useState({ x: 0, y: 0, z: 0 });
  const [tilt, setTilt] = useState({ x: 0, y: 0, z: 0 });

  useEffect(() => {
    let subscriptionAM;
    let subscriptionGS;

    const subscribe = async () => {
      subscriptionGS = Accelerometer.addListener(({ x, y, z }) => {
        setTilt({ x, y, z });
      });

      subscriptionAM = Gyroscope.addListener(({x, y, z}) => {
        setAcceleration({x, y, z});
      });

      Accelerometer.setUpdateInterval(5);
      Gyroscope.setUpdateInterval(100);
    };

    subscribe();

    return () => {
      subscriptionAM && subscriptionAM.remove(); // Cleanup
      subscriptionGS && subscriptionGS.remove();
    }
  }, []);

  const backgroundColor = `rgb(
    ${(tilt.x + 1) * 127}, 
    ${(tilt.y + 1) * 127}, 
    ${(tilt.z + 1) * 127}
  )`;

  return (
    <View style={[styles.container, {backgroundColor}]}>
      <Text>Accelerometer Values:</Text>
      <Text>X: {acceleration.x.toFixed(2)}</Text>
      <Text>Y: {acceleration.y.toFixed(2)}</Text>
      <Text>Z: {acceleration.z.toFixed(2)}</Text>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
