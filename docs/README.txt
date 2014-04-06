watch a project with:
./gather.sh <path to miru.properties>


deployment structure:

miru

-> miru.sh (script to start/stop/restart miru)

-> gather
  -> scripts
    -> gather.sh

-> web
  -> miru.jar

-> properties
  -> miru.properties

