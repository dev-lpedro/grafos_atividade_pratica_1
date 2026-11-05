{ pkgs, lib, config, ... }:

{
  packages = with pkgs; [
    git
  ];

  languages.java.enable = true;
}
