package fireapp.consts

enum class Planet private constructor(private val mass: Double   // in kilograms
                                      , private val radius: Double // in meters
) {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6),
    MARS(6.421e+23, 3.3972e6),
    JUPITER(1.9e+27, 7.1492e7),
    SATURN(5.688e+26, 6.0268e7),
    URANUS(8.686e+25, 2.5559e7),
    NEPTUNE(1.024e+26, 2.4746e7);

    private fun mass(): Double {
        return mass
    }

    private fun radius(): Double {
        return radius
    }

    internal fun surfaceGravity(): Double {
        return G * mass / (radius * radius)
    }

    fun planetInfo(planet: Planet): String {
        val earthWeight = 75
        val mass = earthWeight / EARTH.surfaceGravity()
        return String.format("Your weight on earth is % and on %s is %f%n", 75, planet, planet.surfaceWeight(mass))
    }

    fun position(planet: Planet): Integer {
        return Integer(valueOf(planet.name).ordinal)
    }


    internal fun surfaceWeight(otherMass: Double): Double {
        return otherMass * surfaceGravity()
    }

    companion object {

        // universal gravitational constant  (m3 kg-1 s-2)
        val G = 6.67300E-11

        fun main(args: Array<String>) {
            if (args.size != 1) {
                System.err.println("Usage: java Planet <earth_weight>")
                System.exit(-1)
            }
            val earthWeight = java.lang.Double.parseDouble(args[0])
            val mass = earthWeight / EARTH.surfaceGravity()
            for (p in Planet.values())
                System.out.printf("Your weight on %s is %f%n",
                        p, p.surfaceWeight(mass))
        }
    }

}