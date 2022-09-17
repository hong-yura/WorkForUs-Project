const ModalPortal = (props) => {
  const appRoot = document.getElementById("app");
  return ReactDOM.createPortal(props.children, appRoot);
};
