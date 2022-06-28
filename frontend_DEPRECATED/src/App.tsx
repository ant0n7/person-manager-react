import React from 'react';
import logo from './logo.svg';
import './App.css';
import Home from './ui/pages/Home'
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Layout from './ui/pages/Layout';
import NotFound from './ui/pages/NotFound';
import Students from './ui/pages/Students';
import Subjects from './ui/pages/Subjects';
import Profile from './ui/pages/Profile';
// import * as Bootstrap from "bootstrap";

/**
 * Pages
 *  - New Student account
 *  - New Subject
 *  - Assign subject to student
 *  - Login
 *  - Student self where he sees stuff (profile page)
 * @returns React-DOM
 */

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />} >
          <Route index element={<Home />} />
          <Route path="students" element={<Students />} />
          <Route path="subjects" element={<Subjects />} />
          <Route path="profile" element={<Profile />} />
          <Route path="*" element={<NotFound />} />
        </Route>
      </Routes>
    </BrowserRouter>
    // <div className="App">
    //   <header className="App-header">
    //     <img src={logo} className="App-logo" alt="logo" />
    //     <p>
    //       Edit <code>src/App.tsx</code> and save to reload.
    //     </p>
    //     <a
    //       className="App-link"
    //       href="https://reactjs.org"
    //       target="_blank"
    //       rel="noopener noreferrer"
    //     >
    //       Learn React
    //     </a>
    //   </header>
    // </div>
  );
}

export default App;
