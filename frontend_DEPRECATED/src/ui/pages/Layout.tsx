import React from 'react'
import { Outlet } from 'react-router-dom'
import Navbar from '../organisms/Navbar'
// import bootstrap from 'bootstrap'

type Props = {}

export default function Layout({}: Props) {
  return (
    <div className="container">
        <Navbar />
        <div className="row">
            {/* <div className="col-xl-3"></div> */}
            <div className="col-12 col-xl-12">
                <Outlet />
            </div>
            {/* <div className="col-xl-3"></div> */}
        </div>
    </div>
  )
}