import React from "react";
import { Menu } from "../pages/Menu";
import { Scanner } from "../pages/Scanner";

import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

const Stack = createNativeStackNavigator();

export function AppRoutes() {

    return (
        <NavigationContainer>
            <Stack.Navigator>
                <Stack.Screen name="Menu" component={Menu}
                    options={{ headerShown: false }}
                />
                <Stack.Screen name="Scanner" component={Scanner}
                    options={{
                        headerShown: true,
                        headerTitle: "Leitor",
                    }}
                />
            </Stack.Navigator>
        </NavigationContainer>
    );

}