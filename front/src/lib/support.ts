
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
        (remember ? localStorage : sessionStorage).setItem(key, JSON.stringify({ type: typeof (value), value }));
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
  
  
  