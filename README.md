## Important

This fork of OptiFabric is solely meant to update the 1.14.4 release to properly work with connected textures, the latest [1.14 Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api/files/all?filter-game-version=1738749986%3A64806) and the latest [Fabric Loader](https://fabricmc.net/use/installer/).
I've used code and fixes from later versions of the [official OptiFabric](https://github.com/Chocohead/OptiFabric) to patch this version.  

Full credit goes to [modmuss50](https://github.com/modmuss50) for creating OptiFabric and [Chocohead](https://github.com/Chocohead) for maintaining it, without them this wouldn't have been possible.

__Note:__ This project is not related or supported by either Fabric or OptiFine.  
__Note:__ This project does not contain OptiFine, you must download it separately!

# OptiFabric 1.14.4 Updated [![GitHub Releases](https://img.shields.io/github/downloads/Sjouwer/Optifabric-1.14.4-Updated/total)](https://github.com/Sjouwer/OptiFabric-1.14.4-Updated/releases)
- [Officially approved](https://www.minecraftspeedrunning.com/public-resources/mods) for 1.14.4 speedrunning as long as it's used without Fabric API  
- Works great with texture packs that rely on 1x1 biomes, which are no longer supported on Minecraft 1.15+

![](https://ss.modmuss50.me/javaw_2019-05-22_20-33-34.jpg)

## Installing

After installing Fabric for 1.14.4, you will need to place the OptiFabric mod jar as well as the OptiFine installer in the mods folder.

Fabric Loader should be the latest version from the [Fabric Website](https://fabricmc.net/use/installer/)

If you need more help you can read a more detailed guide [here](https://github.com/modmuss50/OptiFabric/wiki/Install-Tutorial)


## Links
### [Updated 1.14.4 OptiFabric Download](https://github.com/Sjouwer/OptiFabric/releases)

### [All other and official OptiFabric Downloads](https://minecraft.curseforge.com/projects/optifabric)

### [OptiFine Download](https://optifine.net/downloads)

## Issues

If you happen to find an issue and you believe it is to do with this specific version of OptiFabric and not the official OptiFabric, OptiFine or a mod please open an issue [here](https://github.com/Sjouwer/OptiFabric/issues) 


## For Mod Devs

Add the following to your build.gradle

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    modCompile 'com.github.Sjouwer:OptiFabric-1.14.4-Updated:v0.7.3'
} 
```

Put the standard Optifine jar in /run/mods

Class export can be enabled using the following VM Option, this will extract the overwritten classes to the .optifine folder, useful for debugging.

`-Doptifabric.extract=true`

## Screenshots

![](https://ss.modmuss50.me/javaw_2019-05-22_20-36-25.jpg)

![](https://ss.modmuss50.me/javaw_2019-05-22_19-49-41.jpg)

## How it works

This would not have been possible without Chocohead's [Fabric-ASM](https://github.com/Chocohead/Fabric-ASM).

1. The mod looks for an optifine installer or mod jar in the current mods folder
2. If it finds an installer jar it runs the extract task in its own throwaway classloader.
3. The optifine mod jar is a set of classes that need to replace the ones that minecraft provides.
4. Optifine's replacement classes change the name of some lambada methods, so I take a good guess at the old name (using the original minecraft jar).
5. Remap optifine to intermediary (or yarn in development)
6. Move the patched classes out as they wont do much good on the classpath twice
7. Add optifine to the classpath
8. Register the patching tweaker for every class that needs replacing
9. Replace the target class with the class that was extracted, also do some more fixes to it, and make it public (due to access issues).
10. Hope it works
