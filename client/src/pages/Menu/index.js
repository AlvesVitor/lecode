import React from "react";

import { StyleSheet, View, Image, TouchableOpacity, Text } from "react-native";
import Icon from "react-native-vector-icons/dist/MaterialCommunityIcons";
import { useNavigation } from "@react-navigation/native";
import IMG from "../../assets/figure.png";

export function Menu() {
  const navigation = useNavigation();

  function handleOnPress(e) {
    navigation.navigate("Scanner", { type: e });
  }

  return (
    <View style={styles.container}>
      <Image source={IMG} resizeMode="contain" style={styles.img} />
      <View style={styles.areaButton}>
        <TouchableOpacity
          style={styles.button}
          onPress={() => handleOnPress("synchronization")}
        >
          <Icon name="laptop" size={30} color="#3C4F76" />
          <Text style={styles.text}>Sincronizar</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={styles.button}
          onPress={() => handleOnPress("barcode")}
        >
          <Icon name="barcode" size={30} color="#3C4F76" />
          <Text style={styles.text}>Ler Códigos</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "space-around",
    padding: 40,
  },
  img: {
    height: 200,
  },
  areaButton: {
    flexDirection: "row",
    width: "100%",
    justifyContent: "space-between",
  },
  button: {
    padding: 20,
    backgroundColor: "#f5f5f5",
    alignItems: "center",
    justifyContent: "center",
    borderRadius: 10,
    borderColor: "#ebe7e7",
    borderWidth: 1,
  },
  text: {
    color: "#3C4F76",
  },
});
