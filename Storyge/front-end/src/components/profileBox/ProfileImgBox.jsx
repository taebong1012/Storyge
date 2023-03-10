import React, { useState, useRef } from 'react'
import 'react-image-upload/dist/index.css'
import { AiTwotoneCamera } from 'react-icons/ai'
import * as S from './../profileBox/ProfileImgBoxStyle'

export default function ProfileBoxImg({ profileImg, modifyFormData }) {
  const [imgFile, setImgFile] = useState('')
  const imgRef = useRef()

  // 이미지 업로드 input의 onChange
  const saveImgFile = () => {
    const file = imgRef.current.files[0]
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onloadend = () => {
      setImgFile(reader.result)
    }

    modifyFormData(file)
  }

  return (
    <>
      <S.Box className="signup-profileImg-label" htmlFor="profileImg">
        <S.Input
          className="signup-profileImg-input"
          type="file"
          accept="image/*"
          id="profileImg"
          onChange={saveImgFile}
          ref={imgRef}
        />
        <S.Img src={imgFile ? imgFile : profileImg} alt="프로필 이미지" />
        <S.Btn>
          <AiTwotoneCamera />
        </S.Btn>
      </S.Box>
    </>
  )
}
