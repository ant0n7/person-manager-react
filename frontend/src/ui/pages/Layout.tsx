import React from 'react'
import { Outlet } from 'react-router-dom'
import Navbar from '../organisms/Navbar'
// import bootstrap from 'bootstrap'

type Props = {}

export default function Layout({}: Props) {
  return (
    <div className="container-fluid">
        <Navbar />
        <div className="row">
            <div className="col-3"></div>
            <div className="col-6">
                <Outlet />
            </div>
        </div>
    </div>
  )
}