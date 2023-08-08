import type { SvelteComponent } from "svelte";

export interface Authentication {
  token: string;
  principal: Member;
}

export interface Current {
  hash: string;
  refresh: boolean;

  component: typeof SvelteComponent;
};

export interface Link {
  href: string;
  title: string;
}

export interface Member {
  id?: number;
  name: string;
  avatar?: string;
  about?: string;
  gender: "Male" | "Female";
  contacts: { [P in ContactType]?: string };
}

export interface Guild {
  title: string;
  content: string;
  icon: string;
  bg: string;

  parent: number;
  paths: Partial<Guild>[];
  children: Partial<Guild>[];
  defaultPath: number[];
  tags: string[];
  me: Partial<Member>;

  createdAt: number;

  id: number;

}


type ContactType = "email" | "phone" | "kakao";
