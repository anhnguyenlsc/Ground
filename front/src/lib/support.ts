export const FormatDate = (dateString: string) => {
  let date = new Date(dateString);
    let month = (date.getMonth() + 1).toString().padStart(2, '0');
    let day = date.getDate().toString().padStart(2, '0');
    let year = date.getFullYear();
    return `${month}/${day}/${year}`;
}

export const FormatDateTime = (dateString: string) => {
  const date = new Date(dateString);
  const monthNames = ["January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December"];
  const month = monthNames[date.getMonth()];
  const day = date.getDate().toString().padStart(2, '0');
  const hours = date.getHours();
  const minutes = date.getMinutes().toString().padStart(2, '0');
  const ampm = hours >= 12 ? 'PM' : 'AM';
  return `${day} ${month} at ${hours % 12 || 12}:${minutes} ${ampm}`;
}

export const Storage = {
    reset: (key?: string) => {
      if (typeof localStorage !== 'undefined' && typeof sessionStorage !== 'undefined') {
        if (key) {
          localStorage.removeItem(key);
          sessionStorage.removeItem(key);
        }
        else {
            localStorage.clear();
            sessionStorage.clear();
        }
      }
    },
    set: (key: string, value: any, remember: boolean = false) => {
      if (typeof localStorage !== 'undefined' && typeof sessionStorage !== 'undefined') {
        (remember ? sessionStorage : localStorage).setItem(key, JSON.stringify({ value })); //type: typeof (value), 
      }
    },
    get: <T>(key: string): T | undefined => {
      if (typeof localStorage !== 'undefined' && typeof sessionStorage !== 'undefined') {
        const _ = localStorage.getItem(key) || sessionStorage.getItem(key) || undefined;
        if (_) return (JSON.parse(_)).value as T;
        return undefined;
      }
    }
  };

  export const paste = (e: ClipboardEvent) => {
    const clipbord = e.clipboardData?.getData("text/plain");
    if (clipbord && window.getSelection) {
        const selection = window.getSelection();
        if (selection?.rangeCount) {
            const range = selection.getRangeAt(0);
            range.deleteContents();
            range.insertNode(document.createTextNode(clipbord));
        }
    }
};

  
  
  