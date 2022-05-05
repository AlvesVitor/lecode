import React, { useEffect } from "react";
import { useState, useContext } from 'react';
import { StyleSheet, View, FlatList, Text, Alert } from 'react-native';

import service from "../../service";
import { Context } from '../../context';
import { useNavigation } from "@react-navigation/native";

import QRCodeScanner from 'react-native-qrcode-scanner';
import Sound from 'react-native-sound';

import Icon from 'react-native-vector-icons/dist/MaterialCommunityIcons';

import MP3 from "../../assets/audio.mp3";

const ONE_SECOND = 1000;

export function Scanner({ route }) {
  const audio = new Sound(
    MP3,
    error => {
      if (error) {
        console.log('failed to load the sound', error);
        return;
      }
    },
  );
  const { type } = route.params;
  const { url, setUrl } = useContext(Context);
  const [scanner, setScanner] = useState(true);
  const [codes, setcodes] = useState([])
  const navigation = useNavigation();

  useEffect(() => {
    audio.setVolume(1);
    audio.release();
  }, []);

  async function handleBarCodeScanned({ data }) {
    setScanner(false)
    if (type === "barcode") {
      await service.request(data, `${url}/code`, 'POST')
        .then(() => handleSucces(data)
        )
        .catch((e) => alert(e))
    } else {
      await service.request("", `${data}/test`, 'GET')
        .then(() => {
          setUrl(data)
          Alert.alert("Sucesso!", `Sincronizado em ${data}`, [
            {
              text: "OK",
              onPress: () => handleGoBack()
            }
          ])
        })
        .catch((e) => Alert.alert("Erro!", "Verifique se seus aparelhos estão conectados a mesma rede!"))
    }
  };

  function handleGoBack() {
    navigation.goBack()
    setScanner(true)
  }

  async function handleSucces(data) {
    // audio.play()
    let list = codes;
    console.log(codes);
    if(codes.length > 2){
      list.shift();
    }
    list.push(data)
    setcodes(list)
    setTimeout(() => {
      setScanner(true)
    }, ONE_SECOND * 2)
  }

  return (
    <View style={styles.container}>
      <QRCodeScanner
        style={styles.cameraContainer}
        onRead={scanner ? handleBarCodeScanned : undefined}
        reactivate={true}
        showMarker={false}
        bottomContent={
          <FlatList
            style={styles.list}
            showsHorizontalScrollIndicator={true}
            data={codes}
            keyExtractor={item => item + codes.length}
            renderItem={({ item }) => <Text style={styles.code}>{item}</Text>}
          />
        }
      />
      {type === "barcode" ?
        <View style={styles.footer}>
          <View style={styles.areaIcon}>
            <Icon style={styles.iconSync} name="cellphone" size={25} color="#3C4F76" />
            <Icon style={styles.iconSync} name="arrow-right" size={25} color="#3C4F76" />
            <Icon style={styles.iconSync} name="barcode" size={25} color="#3C4F76" />
          </View>
          <Text style={styles.label}>Aponte sua camera para o código de barras.</Text>
        </View>
        :
        <View style={styles.footer}>
          <View style={styles.areaIcon}>
            <Icon style={styles.iconSync} name="cellphone" size={25} color="#3C4F76" />
            <Icon style={styles.iconSync} name="arrow-right" size={25} color="#3C4F76" />
            <Icon style={styles.iconSync} name="laptop" size={25} color="#3C4F76" />
            <Icon style={styles.iconSync} name="arrow-right" size={25} color="#3C4F76" />
            <Icon style={styles.iconSync} name="qrcode-scan" size={25} color="#3C4F76" />
          </View>
          <Text style={styles.label}>Abra o sincronizador no Computador e aponte sua camera para vincular aplicativo.</Text>
        </View>
      }

    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    
    backgroundColor: "#fff",
    alignItems: 'center',
    justifyContent: 'flex-start',
  },
  areaCam: {
    flex: 1
  },
  footer: {
    width: "100%",
    height: 100,
    backgroundColor: "#fff",
    paddingHorizontal: 10,
    alignItems: 'center',
    justifyContent: 'space-around',
  },
  code: {
    color: '#fff',
    textAlign: "left",
    fontSize: 8
  },
  iconSync: {
    paddingHorizontal: 10
  },
  areaIcon: {
    paddingBottom: 5,
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
  },
  label: {
    textAlign: 'center'
  },
  list: {
    width: '100%',
    paddingHorizontal: 10
  }

});