import React, { createContext, useState } from "react";

export const Context = createContext({});

export default function ContextProvider({ children }) {
    const [url, setUrl] = useState('');

    return (
        <Context.Provider value={{
            url,
            setUrl,
        }}>
            {children}
        </Context.Provider>
    )

}