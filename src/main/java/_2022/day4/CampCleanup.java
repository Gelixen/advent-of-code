package _2022.day4;

import lombok.extern.java.Log;
import util.SolvableTask;

import java.util.Arrays;

import static java.util.function.Predicate.not;

@Log
public class CampCleanup implements SolvableTask {
    public static void main(String[] args) {
        new CampCleanup().solve();
    }

    @Override
    public void solve() {
        String[] pairsList = getInputLines();

        long fullyContainingSectorsCount = Arrays.stream(pairsList)
                .map(CampCleanup::toPairModel)
                .filter(not(CampCleanup::isMutuallyExclusive))
                .count();

        log.info(String.valueOf(fullyContainingSectorsCount));
    }

    private static ElfPairSectors toPairModel(String line) {
        String[] pairSectors = line.split(",");

        Sector firstElfSector = getSector(pairSectors[0]);
        Sector secondElfSector = getSector(pairSectors[1]);

        return new ElfPairSectors(firstElfSector, secondElfSector);
    }

    private static Sector getSector(String sector) {
        String[] sectorRange = sector.split("-");

        int sectorStart = Integer.parseInt(sectorRange[0]);
        int sectorEnd = Integer.parseInt(sectorRange[1]);

        return new Sector(sectorStart, sectorEnd);
    }

    private static boolean isMutuallyExclusive(ElfPairSectors elfPairSectors) {
        Sector firstElfSector = elfPairSectors.firstElfSector();
        Sector secondElfSector = elfPairSectors.secondElfSector();

        return oneSectorEndsBeforeAnotherStarts(firstElfSector, secondElfSector)
                || oneSectorEndsBeforeAnotherStarts(secondElfSector, firstElfSector);
    }

    private static boolean oneSectorEndsBeforeAnotherStarts(Sector firstSector, Sector secondSector) {
        return firstSector.end() < secondSector.start();
    }
}
