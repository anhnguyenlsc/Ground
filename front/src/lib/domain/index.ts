import type { SvelteComponent } from "svelte";

export interface Authentication {
  token: string;
  principal: Member;
}

export interface Category {
  _id: string;
  title: string;
}

export interface CategoryType {
  _idType: string;
  ctypeTitle: string
  categoryId: string;
}

export interface Current {
  hash: string;
  refresh: boolean;

  component: typeof SvelteComponent;
};

export interface Link {
  href: string;
  icon?: string;
  title: string;
}

export interface LinkMenu extends Link {
  children?: Link[];
}

export interface User {
  userId: number;
  email: string;
  password: string;
  name: string;
  nickname?: string;
  avatar?: string;
};

export interface Member {
  id?: number;
  name?: string;
  avatar?: string;
  about?: string;
  gender?: "Male" | "Female";
  contacts?: { [P in ContactType]?: string };
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

export interface Circle {
  id: number;
  name: string;
  description: string;
  avatar: string;
  banner: string;
  owner: Partial<User>;
  rules: { [R in CircleRules]?: boolean };

  parent: number;
  children: Partial<Circle>[];

  type: string;
  typeId: string;
  tags: string[];
  status: CircleStatus;
  banned: Partial<User>;
  muted: Partial<User>;
  counter: CircleCounter;
  settings: CircleSettings;
  createdAt: number;
  modifiedAt: number;
}

export interface Post {
  member: Member;
  circleId: number;
  circleName: string;
  title: string;
  content: string;
  image: string[];
  tags: string[];
  counter: PostCounter;
  comments: Comment[];
  share: Partial<Member>[];
  createdAt: number;
}

export interface Collection {
  memberId: number;
  name: string;
  savedPosts: Partial<Post>[];
}

export interface Comment {
  id: number;
  postId: number;
  member: Member;
  content: string;
}

export interface PostCounter {
  like: number;
  unlike: number;
  comment: number;
  share: number;
  saved: number;
}

type CircleStatus = "Open" | "Closed";

export interface CircleCounter {
  rate: number;
  member: number;
}

export interface CircleSettings {

}

export enum CircleRules {
  Public = "Public",
  Restricted = "Restricted",
  Private = "Private",
  AdultsOnly = "Only adults are allowed to access"
}


type ContactType = "email" | "phone" | "kakao";
