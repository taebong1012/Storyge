import styled from "styled-components";

export const container = styled.div`
  margin: 0 20px 0 20px;
  // border: 1px solid red;
`;

export const card = styled.div`
  border-radius: 20px;
  border: 3px solid var(--color-primary);
  background-color: ${(props) => props.backgroundColor};
  margin: 20px 0 20px 0;
  padding: 2px;
  width: 100%;
  height: 230px;
`;

export const TextBox = styled.textarea`
  text-align: top;
  box-sizing: border-box;
  width: 100%;
  height: 80%;
  padding: auto;
  border: none;
  border-radius: 20px 20px 0 0;
  padding: 10px 20px 0 20px;
  font-size: 15px;
  background-color: ${(props) => props.backgroundColor};
  outline-color: transparent; //textarea onfocus 됐을 때 보더 없앰
`;

export const CardFoot = styled.div`
  height: 10%;
  width: 100%;
  display: flex;
  background-color: ${(props) => props.backgroundColor};
  height: ${(props) => props.height};
  border-radius: 0 0 20px 20px;
  justify-content: space-between;
  align-items: center;
`;

export const CountDiary = styled.div`
  margin: 0 0 0 10px;
`;

export const Test = styled.div`
  margin: 0 0 0 7px;
`;
