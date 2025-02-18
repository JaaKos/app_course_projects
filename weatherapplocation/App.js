import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Button, Alert, Image } from 'react-native';
import { Ionicons } from '@expo/vector-icons'; 
import { Header } from '@rneui/themed';
import { MaterialCommunityIcons, FontAwesome5, FontAwesome6 } from '@expo/vector-icons';
import React, { useState } from 'react';
import Dialog from 'react-native-dialog';  // Importing react-native-dialog

import * as Location from 'expo-location';


export default function App() {
  const apiKey = '';

  const [dialogVisible, setDialogVisible] = useState(false); // State to control dialog visibility
  
  const [cityName, setCityName] = useState("Tampere");
  const [weatherDescription, setWeatherDescription] = useState("");
  const [newCity, setNewCity] = useState(""); // State to capture the new city name
  const [newTemp, setNewTemp] = useState(0);
  const [newWindspeed, setNewWindspeed] = useState(0);
  const [weatherIcon, setWeatherIcon] = useState(null); // New state for the weather icon


  const handleChangeCity = () => {
    setDialogVisible(true); // Show the dialog when the button is pressed
  };

  const handleDialogCancel = () => {
    setDialogVisible(false); // Close the dialog without changing the city name
  };

  const handleDialogConfirm = () => {
    setCityName(newCity); // Update the city name with the value from the input
    setDialogVisible(false); // Close the dialog
  };

  const fetchWeather = async () => {

    if (cityName === "Sijainti") {
      fetchLocation();
      return;
    }
   
    const response = await fetch(
      `https://api.openweathermap.org/data/2.5/weather?q=${cityName}&appid=${apiKey}&units=metric`
    );
    const data = await response.json();

    
    if (response.ok) {
      ToastAndroid.showWithGravity("Fetching successful!", ToastAndroid.SHORT, ToastAndroid.TOP);
      // Extract temperature and wind speed
      setNewTemp(data.main.temp); 
      setNewWindspeed(data.wind.speed);
  
      // Extract weather description and icon code
      const weatherDescription = data.weather[0].description;
      const weatherIconCode = data.weather[0].icon; // Example: "10d" for rain
  
      // Build the icon image URL from the OpenWeatherMap icon code
      const iconUrl = `https://openweathermap.org/img/wn/${weatherIconCode}@2x.png`;
  
      // Set the weather icon URL to state (to display the image)
      setWeatherIcon(iconUrl);
      setWeatherDescription(weatherDescription);

    } else {
      Alert.alert('Error', 'City not found or API error');
    }
  };

  const fetchLocation = async () => {
    let { status } = await Location.requestForegroundPermissionsAsync();
    if (status !== 'granted') {
      ToastAndroid.showWithGravity("ERROR: Permission to access location was denied!", ToastAndroid.SHORT, ToastAndroid.TOP);
      return;
    }

    const location = await Location.getCurrentPositionAsync({});
    const {latitude, longitude} = location.coords; 

    const response = await fetch(
      `https://api.openweathermap.org/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=${apiKey}&units=metric`
    );
  
    const data = await response.json();

    if (response.ok) {
      ToastAndroid.showWithGravity("Fetching successful!", ToastAndroid.SHORT, ToastAndroid.TOP);
      setNewTemp(data.main.temp);
      setNewWindspeed(data.wind.speed);
      setWeatherDescription(data.weather[0].description);
      setCityName("Sijainti");
      
      // Get icon URL
      const iconUrl = `https://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png`;
      setWeatherIcon(iconUrl);

    } else {
      Alert.alert("Error", "Failed to fetch weather data.");
    }  
  }
  


  return (
    <View style={styles.container}>

    <Dialog.Container visible={dialogVisible}>
        <Dialog.Title>Enter a new city</Dialog.Title>
        <Dialog.Input
          placeholder="City name"
          value={newCity}
          onChangeText={setNewCity} // Update newCity state on text change
        />
        <Dialog.Button label="Cancel" onPress={handleDialogCancel} />
        <Dialog.Button label="OK" onPress={handleDialogConfirm} />
    </Dialog.Container>

      <Header
        centerComponent = {
          <View> 
            <View style={styles.elementRow}>
              <Text style={[styles.largerText, styles.box]}> {cityName} </Text>
              <Button
                title="Vaihda"
                onPress={handleChangeCity} // Show the prompt when pressed
              />
            </View>
            <Text style={[styles.largerText, styles.box]}> {weatherDescription} </Text>
          </View>
        } containerStyle = {styles.header}
      />
      <View style={styles.weatherScreen}>

       <View style={styles.elementRow}>
        <Image 
          source={{ uri: weatherIcon }} // Use the weather icon URL
          style={{ width: 100, height: 100 }} // Adjust size as needed
        />
        </View>

        <View style={styles.elementRow}>
          <FontAwesome6 name="temperature-half" size={50} color="black" />
          <Text style={{fontSize: 32}}>{newTemp}</Text>
          <MaterialCommunityIcons name='temperature-celsius' size={50} color="black"/>
        </View>

        <View style={styles.elementRow}>
          <FontAwesome5 name="wind" size={50} color="black" />
          <Text style={{fontSize: 32}}>{newWindspeed} km/h</Text>
        </View>
        
      </View>

      <View style={styles.elementRow}>
        <Button
          title="Päivitä"
          onPress={fetchWeather}
          icon={<Ionicons name="refresh" size={24} color="white" />}
        />
        <Button
          title='Sijainti'
          onPress={fetchLocation}
        />
      </View>
      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
  header: {
    backgroundColor: 'lightgray',
    height: '20%',
  },
  largerText: {
    fontSize: 18
  },
  box: {
    padding: 10
  },
  weatherScreen: {
    height: '60%'
  },
  button: {
    height: '20%'
  },
  elementRow: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
    gap: 10
  }
});
